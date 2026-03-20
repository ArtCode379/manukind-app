package shop.manukindapp.com.ui.composable.screen.productdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.manukindapp.com.R
import shop.manukindapp.com.data.model.Product
import shop.manukindapp.com.ui.composable.shared.DataBasedContainer
import shop.manukindapp.com.ui.composable.shared.DataEmptyContent
import shop.manukindapp.com.ui.state.DataUiState
import shop.manukindapp.com.ui.theme.AccentGold
import shop.manukindapp.com.ui.theme.PrimaryDark
import shop.manukindapp.com.ui.theme.TextSecondary
import shop.manukindapp.com.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    DataBasedContainer(
        dataState = productState,
        modifier = modifier,

        dataPopulated = {
            val product = (productState as DataUiState.Populated<Product>).data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(product.imageRes),
                    contentDescription = stringResource(R.string.product_image_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier.padding(20.dp),
                ) {
                    // Category badge
                    Box(
                        modifier = Modifier
                            .background(
                                AccentGold.copy(alpha = 0.15f),
                                RoundedCornerShape(20.dp),
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = stringResource(product.category.titleRes),
                            color = AccentGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "£%.2f".format(product.price),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentGold,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSecondary,
                        lineHeight = 24.sp,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = onAddToCart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccentGold,
                            contentColor = PrimaryDark,
                        ),
                    ) {
                        Text(
                            text = stringResource(R.string.button_add_to_cart_label),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}
