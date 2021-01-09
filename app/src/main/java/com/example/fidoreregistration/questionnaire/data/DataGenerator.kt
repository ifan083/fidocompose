package com.example.fidoreregistration.questionnaire.data

import com.example.fidoreregistration.questionnaire.logic.*
import com.example.fidoreregistration.wizard.Wizard

object DataGenerator {

    fun reRegistrationStep(step: Wizard.Destination) =
        when (step) {
            ReRegistration.SetupTouchIdLogin -> SetupTouchIdLogin()
            ReRegistration.ActivateFaceId -> ActivateFaceId()
            ReRegistration.ActivatePush -> ActivatePushNotifications()
            ReRegistration.ExistingCustomerCheck -> ExistingCustomerCheck()
            ReRegistration.OrderEFinance -> OrderEFinance()
            else -> AppStart()
        }
}