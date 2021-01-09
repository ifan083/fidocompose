package com.example.fidoreregistration.questionnaire.data

import com.example.fidoreregistration.questionnaire.ui.EUser
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action
import com.example.fidoreregistration.wizard.Wizard
import java.io.Serializable


sealed class ReRegistrationEntry : Wizard.Entry, Serializable {
    object AppStart : ReRegistrationEntry()
    object SetupTouchIdLogin : ReRegistrationEntry()
    object ActivatePermissions : ReRegistrationEntry()
    object OrderEFinance : ReRegistrationEntry()
}

sealed class ReRegistration : Wizard.Destination, Serializable {
    object AppStart : ReRegistration()
    object ExistingCustomerCheck : ReRegistration()
    object OrderEFinance : ReRegistration()
    object SetupTouchIdLogin : ReRegistration()
    object ActivatePush : ReRegistration()
    object ActivateFaceId : ReRegistration()
}

sealed class ReRegistrationFragment : Wizard.FragmentDestination, Serializable {
    data class Questionnaire(val entry: ReRegistrationEntry) : ReRegistrationFragment()
    class FidoRegistration(val eUser: EUser) : ReRegistrationFragment()
    object NativeLogin : ReRegistrationFragment()
    object RegistrationMethodChooser : ReRegistrationFragment()
    object Activate3DSCardSelection : ReRegistrationFragment()
    object Activate3DSCredentials : ReRegistrationFragment()
    data class Complete(val title: String, val subtitle: String) : ReRegistrationFragment()
}

sealed class ReRegistrationActivity : Wizard.ActivityDestination, Serializable {
    object OnBoarding : ReRegistrationActivity()
    object AssetOverview : ReRegistrationActivity()
    object WelcomeScreen : ReRegistrationActivity()
}

interface WizardStep {
    fun destination(): Wizard.Destination
    fun screenDescription(): QuestionScreenDescription
    fun positiveAction(): Action
    fun negativeAction(): Action
}

