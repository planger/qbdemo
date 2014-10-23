package controllers

import model.TaskSchema
import org.qbproject.schema.QBSchema._
import org.qbproject.controllers.QBCrudController
import org.qbproject.mongo.{ QBCollectionValidation, QBMongoCollection }
import org.qbproject.routing.{ QBRouter, QBRoute }
import play.modules.reactivemongo.MongoController

object TaskController extends MongoController
  with QBCrudController with QBRouter {

  override def collection: QBCollectionValidation =
    new QBMongoCollection("tasks")(db) with QBCollectionValidation {
      override def schema = TaskSchema.task
    }

  override def qbRoutes: List[QBRoute] = crudRoutes
  
  override def createSchema = TaskSchema.task - "id"
} 