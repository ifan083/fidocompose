package com.example.fidoreregistration.methodchooser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.MobileScreenShare
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.example.fidoreregistration.ActivityNavigation
import com.example.fidoreregistration.BaseFragment
import com.example.fidoreregistration.FragmentNavigation
import com.example.fidoreregistration.methodchooser.ui.Chooser
import com.example.fidoreregistration.methodchooser.ui.ItemData
import com.example.fidoreregistration.questionnaire.data.ReRegistrationActivity
import com.example.fidoreregistration.questionnaire.data.ReRegistrationEntry
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.questionnaire.ui.EUser
import com.example.fidoreregistration.theme.FidoReregistrationTheme

class RegistrationMethodChooser : BaseFragment() {

    companion object {
        fun create(): RegistrationMethodChooser {
            return RegistrationMethodChooser()
        }
    }

    private enum class Method(val id: Int) {
        QrCode(1),
        FidoTransfer(2),
        CardReader(3),
        NoLoginMethod(4)
    }

    private lateinit var activityNavigation: ActivityNavigation
    private lateinit var fragmentNavigation: FragmentNavigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityNavigation = context as ActivityNavigation
        fragmentNavigation = context as FragmentNavigation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FidoReregistrationTheme {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    ) {
                        Chooser(
                            items = registrationMethods(),
                            onItemClicked = ::navigate,
                            onBackClick = { fragmentNavigation.goBack() },
                            onExitClick = { activityNavigation.toActivity(ReRegistrationActivity.WelcomeScreen) }
                        )
                    }
                }
            }
        }
    }

    private fun navigate(id: Int) {
        when (id) {
            //send EUser
            Method.QrCode.id -> {
                val eUser = EUser(
                    etn = "etn",
                    efUserId = "efUserId",
                    username = "username",
                    userOid = "oid"
                )
                fragmentNavigation.toFragment(ReRegistrationFragment.FidoRegistration(eUser))
            }
            Method.NoLoginMethod.id -> {
                val fragmentDestination =
                    ReRegistrationFragment.Questionnaire(ReRegistrationEntry.OrderEFinance)
                fragmentNavigation.toFragment(fragmentDestination)
            }
            Method.CardReader.id -> { /* todo find where to go */
            }
            Method.FidoTransfer.id -> { /* todo find where to go */
            }
        }
    }

    private fun registrationMethods() = listOf(
        ItemData(
            id = Method.QrCode.id,
            title = "QR Code",
            subtitle = "You have received a QR activation code",
            img = Icons.Default.QrCodeScanner,
        ),
        ItemData(
            id = Method.FidoTransfer.id,
            title = "Other smartphone",
            subtitle = "You have access to another device with an activated login",
            img = Icons.Default.MobileScreenShare,
        ),
        ItemData(
            id = Method.CardReader.id,
            title = "Card reader / Mobile ID",
            subtitle = "You have a card reader or Mobile ID",
            img = Icons.Default.CreditCard,
        ),
        ItemData(
            id = Method.NoLoginMethod.id,
            title = "Without a login method",
            subtitle = "Show me other options",
            img = Icons.Default.Settings,
        )
    )


}