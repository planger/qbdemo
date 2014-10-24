package model

import org.qbproject.mongo.objectId
import org.qbproject.schema.QBSchema
import QBSchema._

object UserSchema {
  
  val user = qbClass(
    "id" -> objectId,
    "name" -> qbNonEmptyText,
    "status" -> qbEnum("ACTIVE", "PASSIVE"),
    "email" -> qbEmail)
    
}