package shop.manukindapp.com.ui.composable.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shop.manukindapp.com.R
import shop.manukindapp.com.ui.composable.shared.DataBasedContainer
import shop.manukindapp.com.ui.composable.shared.DataEmptyContent
import shop.manukindapp.com.ui.state.CartItemUiState
import shop.manukindapp.com.ui.state.DataUiState
import shop.manukindapp.com.ui.theme.AccentGold
import shop.manukindapp.com.ui.theme.PrimaryDark
import shop.manukindapp.com.ui.theme.TextSecondary
import shop.manukindapp.com.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    val onPlusItemClick = { itemId: Int ->
        viewModel.incrementProductInCart(itemId)
    }

    val onMinusItemClick = { itemId: Int ->
        viewModel.decrementItemInCart(itemId)
    }

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = onPlusItemClick,
        onMinusItemClick = onMinusItemClick,
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    DataBasedContainer(
        dataState = cartItemsState,
        modifier = modifier,

        dataPopulated = {
            val items = (cartItemsState as DataUiState.Populated<List<CartItemUiState>>).data

            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    items(items, key = { it.productId }) { item ->
                        CartItemCard(
                            item = item,
                            onPlusClick = { onPlusItemClick(item.productId) },
                            onMinusClick = { onMinusItemClick(item.productId) },
                        )
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }
                }

                HorizontalDivider()

                // Total and checkout button
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.cart_total_label),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "£%.2f".format(totalPrice),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = AccentGold,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onCompleteOrderButtonClick,
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
                            text = stringResource(R.string.button_place_order_label),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.cart_state_empty_primary_text),
                secondaryText = stringResource(R.string.cart_state_empty_secondary_text),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun CartItemCard(
    item: CartItemUiState,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            item.productImageRes?.let { imageRes ->
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = item.productTitle,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.productTitle,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "£%.2f".format(item.productPrice),
                    style = MaterialTheme.typography.bodyMedium,
                    color = AccentGold,
                    fontWeight = FontWeight.Bold,
                )
            }

            // Quantity controls
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = onMinusClick,
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus_svgrepo_com),
                        contentDescription = stringResource(R.string.decrease_quantity_icon_description),
                        modifier = Modifier.size(16.dp),
                        tint = TextSecondary,
                    )
                }

                Text(
                    text = item.quantity.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 8.dp),
                )

                IconButton(
                    onClick = onPlusClick,
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus_svgrepo_com),
                        contentDescription = stringResource(R.string.increase_quantity_icon_description),
                        modifier = Modifier.size(16.dp),
                        tint = AccentGold,
                    )
                }
            }
        }
    }
}
