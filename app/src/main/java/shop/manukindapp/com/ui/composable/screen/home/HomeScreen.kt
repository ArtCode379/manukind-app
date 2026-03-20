package shop.manukindapp.com.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.manukindapp.com.R
import shop.manukindapp.com.data.model.Product
import shop.manukindapp.com.data.model.ProductCategory
import shop.manukindapp.com.ui.composable.shared.DataBasedContainer
import shop.manukindapp.com.ui.composable.shared.DataEmptyContent
import shop.manukindapp.com.ui.state.DataUiState
import shop.manukindapp.com.ui.theme.AccentGold
import shop.manukindapp.com.ui.theme.PrimaryDark
import shop.manukindapp.com.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    DataBasedContainer(
        dataState = productsState,
        modifier = modifier,

        dataPopulated = {
            val products = (productsState as DataUiState.Populated<List<Product>>).data
            var selectedCategory: ProductCategory? by remember { mutableStateOf(null) }

            val filteredProducts = if (selectedCategory == null) products
            else products.filter { it.category == selectedCategory }

            val featuredProducts = products.take(4)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // Featured carousel
                item(span = { GridItemSpan(2) }) {
                    FeaturedCarousel(
                        products = featuredProducts,
                        onProductClick = onNavigateToProductDetails,
                    )
                }

                // Category chips
                item(span = { GridItemSpan(2) }) {
                    CategoryChips(
                        selectedCategory = selectedCategory,
                        onCategorySelected = { selectedCategory = it },
                    )
                }

                // Product grid
                items(filteredProducts, key = { it.id }) { product ->
                    ProductGridItem(
                        product = product,
                        onClick = { onNavigateToProductDetails(product.id) },
                        onAddToCart = { onAddProductToCart(product.id) },
                    )
                }

                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.products_state_empty_primary_text),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun FeaturedCarousel(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
) {
    Column {
        Text(
            text = stringResource(R.string.featured_section_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp),
        )

        val pagerState = rememberPagerState(pageCount = { products.size })

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            pageSpacing = 12.dp,
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onProductClick(products[page].id) },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(products[page].imageRes),
                        contentDescription = products[page].title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.35f))
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp),
                    ) {
                        Text(
                            text = products[page].title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        Text(
                            text = "£%.2f".format(products[page].price),
                            color = AccentGold,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }

        // Pager indicators
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(products.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (index == pagerState.currentPage) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == pagerState.currentPage) AccentGold
                            else AccentGold.copy(alpha = 0.3f)
                        )
                )
            }
        }
    }
}

@Composable
private fun CategoryChips(
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory?) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        item {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { onCategorySelected(null) },
                label = { Text(stringResource(R.string.category_all)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AccentGold,
                    selectedLabelColor = PrimaryDark,
                ),
            )
        }
        items(ProductCategory.entries) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(stringResource(category.titleRes)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AccentGold,
                    selectedLabelColor = PrimaryDark,
                ),
            )
        }
    }
}

@Composable
private fun ProductGridItem(
    product: Product,
    onClick: () -> Unit,
    onAddToCart: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier.padding(12.dp),
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "£%.2f".format(product.price),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = AccentGold,
                    )

                    IconButton(
                        onClick = onAddToCart,
                        modifier = Modifier.size(32.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.plus_svgrepo_com),
                            contentDescription = stringResource(R.string.button_add_to_cart_label),
                            modifier = Modifier.size(20.dp),
                            tint = AccentGold,
                        )
                    }
                }
            }
        }
    }
}
