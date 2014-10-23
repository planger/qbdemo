package model

import org.qbproject.mongo.objectId
import org.qbproject.schema.QBSchema
import QBSchema._

object TaskSchema {
  
  val user = qbClass(
    "id" -> objectId,
    "name" -> qbNonEmptyText)

  val task = qbClass(
    "id" -> objectId,
    "name" -> qbNonEmptyText,
    "dueDate" -> optional(qbDateTime),
    "done" -> qbBoolean,
    "assignee" -> qbString)
    
}