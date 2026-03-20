package shop.manukindapp.com.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import shop.manukindapp.com.R
import shop.manukindapp.com.data.entity.OrderEntity
import shop.manukindapp.com.ui.composable.shared.DataBasedContainer
import shop.manukindapp.com.ui.composable.shared.DataEmptyContent
import shop.manukindapp.com.ui.state.DataUiState
import shop.manukindapp.com.ui.theme.AccentGold
import shop.manukindapp.com.ui.theme.TextSecondary
import shop.manukindapp.com.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
        onDeleteOrder = viewModel::deleteOrder,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
    onDeleteOrder: (String) -> Unit,
) {
    DataBasedContainer(
        dataState = ordersState,
        modifier = modifier,

        dataPopulated = {
            val orders = (ordersState as DataUiState.Populated<List<OrderEntity>>).data

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }

                items(orders, key = { it.orderNumber }) { order ->
                    OrderCard(
                        order = order,
                        onDelete = { onDeleteOrder(order.orderNumber) },
                    )
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.orders_state_empty_primary_text),
                secondaryText = stringResource(R.string.orders_state_empty_secondary_text),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun OrderCard(
    order: OrderEntity,
    onDelete: () -> Unit,
) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
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
                    text = stringResource(R.string.order_number, order.orderNumber),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.trash_svgrepo_com),
                        contentDescription = stringResource(R.string.delete_item_icon_description),
                        modifier = Modifier.size(18.dp),
                        tint = TextSecondary,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.timestamp.format(dateFormatter),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                maxLines = 3,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(
                        R.string.order_customer,
                        order.customerFirstName,
                        order.customerLastName,
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                )
                Text(
                    text = "£%.2f".format(order.price),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = AccentGold,
                )
            }
        }
    }
}
