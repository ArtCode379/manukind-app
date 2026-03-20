package shop.manukindapp.com.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shop.manukindapp.com.R
import shop.manukindapp.com.data.entity.OrderEntity
import shop.manukindapp.com.ui.state.DataUiState
import shop.manukindapp.com.ui.theme.AccentGold
import shop.manukindapp.com.ui.theme.PrimaryDark
import shop.manukindapp.com.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalidState by viewModel.emailInvalidState.collectAsStateWithLifecycle()

    val isButtonEnabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotEmpty() &&
                    viewModel.customerLastName.isNotEmpty() &&
                    viewModel.customerEmail.isNotEmpty()
        }
    }

    if (orderState is DataUiState.Populated) {
        CheckoutDialog(
            order = (orderState as DataUiState.Populated<OrderEntity>).data,
            onConfirm = onNavigateToOrdersScreen
        )
    }

    CheckoutContent(
        customerFirstName = viewModel.customerFirstName,
        customerLastName = viewModel.customerLastName,
        customerEmail = viewModel.customerEmail,
        customerPhone = viewModel.customerPhone,
        customerAddress = viewModel.customerAddress,
        isEmailInvalid = emailInvalidState,
        modifier = modifier,
        focusManager = focusManager,
        isButtonEnabled = isButtonEnabled,
        onFirstNameChanged = viewModel::updateCustomerFirstName,
        onLastNameChanged = viewModel::updateCustomerLastName,
        onEmailChanged = viewModel::updateCustomerEmail,
        onPhoneChanged = viewModel::updateCustomerPhone,
        onAddressChanged = viewModel::updateCustomerAddress,
        onPlaceOrderButtonClick = viewModel::placeOrder
    )
}

@Composable
private fun CheckoutContent(
    customerFirstName: String,
    customerLastName: String,
    customerEmail: String,
    customerPhone: String,
    customerAddress: String,
    isEmailInvalid: Boolean,
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    isButtonEnabled: Boolean,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onAddressChanged: (String) -> Unit,
    onPlaceOrderButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Shipping Information",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp),
        )

        CheckoutTextField(
            input = customerFirstName,
            onInputChange = onFirstNameChanged,
            labelText = stringResource(R.string.checkout_text_field_first_name),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        Spacer(modifier = Modifier.height(12.dp))

        CheckoutTextField(
            input = customerLastName,
            onInputChange = onLastNameChanged,
            labelText = stringResource(R.string.checkout_text_field_last_name),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        Spacer(modifier = Modifier.height(12.dp))

        CheckoutTextField(
            input = customerEmail,
            onInputChange = onEmailChanged,
            labelText = stringResource(R.string.checkout_text_field_email),
            modifier = Modifier.fillMaxWidth(),
            isError = isEmailInvalid,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
        )

        Spacer(modifier = Modifier.height(12.dp))

        CheckoutTextField(
            input = customerPhone,
            onInputChange = onPhoneChanged,
            labelText = stringResource(R.string.checkout_text_field_phone),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next,
            ),
        )

        Spacer(modifier = Modifier.height(12.dp))

        CheckoutTextField(
            input = customerAddress,
            onInputChange = onAddressChanged,
            labelText = stringResource(R.string.checkout_text_field_address),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                onPlaceOrderButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = isButtonEnabled,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentGold,
                contentColor = PrimaryDark,
                disabledContainerColor = AccentGold.copy(alpha = 0.4f),
                disabledContentColor = PrimaryDark.copy(alpha = 0.4f),
            ),
        ) {
            Text(
                text = stringResource(R.string.button_confirm_order_label),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier,
        enabled = enabled,
        label = {
            Text(
                text = labelText,
                style = MaterialTheme.typography.titleSmall,
            )
        },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedBorderColor = AccentGold,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = AccentGold,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = AccentGold,
        ),
    )
}
