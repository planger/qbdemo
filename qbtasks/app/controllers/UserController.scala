package controllers

import org.qbproject.controllers.JsonHeaders
import org.qbproject.controllers.QBCrudController
import org.qbproject.mongo.QBCollectionValidation
import org.qbproject.mongo.QBMongoCollection
import org.qbproject.routing.QBRouter
import org.qbproject.schema.QBSchema.QBSchemaOps
import org.qbproject.schema.QBSchema.qbTypeWriter

import model.UserSchema
import play.api.libs.json.Json
import play.api.mvc.Action
import play.modules.reactivemongo.MongoController

object UserController extends MongoController
  with QBCrudController with QBRouter {

  override def collection: QBCollectionValidation =
    new QBMongoCollection("users")(db) with QBCollectionValidation {
      override def schema = UserSchema.user
    }

  override def qbRoutes = UserController.crudRoutes

  override def createSchema = UserSchema.user - "id"

  def view = JsonHeaders {
    Action {
      Ok(Json.toJson(UserSchema.userView))
    }
  }

  def model = JsonHeaders {
    Action {
      Ok(Json.toJson(UserSchema.user))
    }
  }
  
}
