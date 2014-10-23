package controllers

import model.TaskSchema
import org.qbproject.schema.QBSchema._ 
import org.qbproject.controllers.QBCrudController
import org.qbproject.mongo.{ QBCollectionValidation, QBMongoCollection }
import org.qbproject.routing.{ QBRouter, QBRoute }
import play.modules.reactivemongo.MongoController

object UserController extends MongoController
  with QBCrudController with QBRouter {

  override def collection: QBCollectionValidation =
    new QBMongoCollection("users")(db) with QBCollectionValidation {
      override def schema = TaskSchema.user
    }
  
  override def qbRoutes = UserController.crudRoutes
  
  override def createSchema = TaskSchema.user - "id"
}
