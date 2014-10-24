package model

import org.qbproject.mongo.objectId
import org.qbproject.schema.QBSchema.qbClass
import org.qbproject.schema.QBSchema.qbEmail
import org.qbproject.schema.QBSchema.qbEnum
import org.qbproject.schema.QBSchema.qbNonEmptyText

import model.QBView.QBViewControl
import model.QBView.QBViewModel
import model.QBView.QBViewPath

object UserSchema {

  val user = qbClass(
    "id" -> objectId,
    "name" -> qbNonEmptyText,
    "status" -> qbEnum("ACTIVE", "PASSIVE"),
    "email" -> qbEmail)

  val userView = QBViewModel(
    user,
    QBViewControl("name", QBViewPath("name")),
    QBViewControl("status", QBViewPath("status")),
    QBViewControl("email", QBViewPath("email")))

}