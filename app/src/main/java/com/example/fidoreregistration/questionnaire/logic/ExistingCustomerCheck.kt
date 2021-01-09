package com.example.fidoreregistration.questionnaire.logic

import com.example.fidoreregistration.questionnaire.data.ReRegistration
import com.example.fidoreregistration.questionnaire.data.ReRegistrationActivity
import com.example.fidoreregistration.questionnaire.data.WizardStep
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action

class ExistingCustomerCheck : WizardStep {

    override fun destination() = ReRegistration.ExistingCustomerCheck

    override fun screenDescription() = QuestionScreenDescription(
        title = "PostFinance customer",
        subtitle = "Are you already a PostFinance customer?",
        posBtnText = "Yes, I'm already a customer",
        negBtnText = "Not yet, I want to become one",
        imgUrl = QuestionScreenDescription.Img.HOUSE.url,
        canGoBack = false,
        canExit = true
    )

    override fun positiveAction() = Action.Continue(ReRegistration.OrderEFinance)

    override fun negativeAction() = Action.Finish.ToActivity(ReRegistrationActivity.OnBoarding)

}