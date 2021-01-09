package com.example.fidoreregistration.questionnaire.logic

import com.example.fidoreregistration.questionnaire.data.ReRegistration
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.questionnaire.data.WizardStep
import com.example.fidoreregistration.questionnaire.ui.EUser
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action
import com.example.fidoreregistration.wizard.Wizard

class ActivatePushNotifications : WizardStep {

    override fun destination() = ReRegistration.ActivatePush

    override fun screenDescription() = QuestionScreenDescription(
        title = "Activate push notifications",
        subtitle = "We need to be able to send you push notifications for the new login.",
        posBtnText = "Activate push notifications",
        negBtnText = null,
        canExit = true,
        canGoBack = true,
        imgUrl = QuestionScreenDescription.Img.PERMISSIONS.url
    )

    override fun positiveAction(): Action {
        if (!Util.isFaceIdEnabled()) {
            return Action.Continue(ReRegistration.ActivateFaceId)
        }
        if (!Util.isUserLoggedIn()) {
            return Action.Finish.ToFragment(ReRegistrationFragment.RegistrationMethodChooser)
        }
        return Action.Finish.ToFragment(ReRegistrationFragment.FidoRegistration(EUser()))
    }

    override fun negativeAction() = Action.Continue(Wizard.NoDestination)
}