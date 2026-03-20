package shop.manukindapp.com.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import shop.manukindapp.com.R
import shop.manukindapp.com.ui.theme.AccentGold
import shop.manukindapp.com.ui.theme.TextSecondary

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                // Company
                SettingsRow(
                    icon = R.drawable.user_2_svgrepo_com,
                    label = stringResource(R.string.settings_screen_company_label),
                    value = stringResource(R.string.company_name),
                )

                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                // Version
                SettingsRow(
                    icon = R.drawable.star_svgrepo_com,
                    label = stringResource(R.string.settings_screen_version_label),
                    value = stringResource(R.string.app_version),
                )

                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                // Customer Support
                SettingsRow(
                    icon = R.drawable.link_svgrepo_com,
                    label = stringResource(R.string.settings_screen_customer_support_label),
                    value = stringResource(R.string.customer_support_link),
                    isLink = true,
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(context.getString(R.string.customer_support_link))
                        )
                        context.startActivity(intent)
                    },
                )
            }
        }
    }
}

@Composable
private fun SettingsRow(
    icon: Int,
    label: String,
    value: String,
    isLink: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .let { if (onClick != null) it.clickable { onClick() } else it }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(22.dp),
            tint = AccentGold,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = if (isLink) AccentGold else MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
