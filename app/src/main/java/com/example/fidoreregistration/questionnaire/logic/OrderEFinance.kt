package com.example.fidoreregistration.questionnaire.logic

import com.example.fidoreregistration.common.Strings
import com.example.fidoreregistration.questionnaire.data.ReRegistration
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.questionnaire.data.WizardStep
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action
import com.example.fidoreregistration.wizard.Wizard

class OrderEFinance : WizardStep {

    override fun destination() = ReRegistration.OrderEFinance

    override fun screenDescription() = QuestionScreenDescription(
        title = "Order e-finance login",
        subtitle = "In order to use this app, you need an e-finance login",
        posBtnText = "Order e-finance",
        negBtnText = null,
        canGoBack = true,
        canExit = true,
        imgUrl = QuestionScreenDescription.Img.HOUSE.url
    )

    override fun positiveAction(): Action {
        //todo perform order e-finance
        return Action.Finish.ToFragment(
            ReRegistrationFragment.Complete(
                title = Strings.qrCodeOrderedTitle(),
                subtitle = Strings.qrCodeOrderedSubtitle()
            )
        )
    }

    //navigates to nowhere
    override fun negativeAction() = Action.Continue(Wizard.NoDestination)
}
