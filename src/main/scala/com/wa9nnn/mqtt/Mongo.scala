
package com.wa9nnn.mqtt

import com.mongodb.WriteConcern
import com.typesafe.scalalogging.LazyLogging
import org.bson.Document
class Mongo extends LazyLogging{

  val connectUri: String = "mongodb://localhost"
  val dbName: String = "fountain"


  import com.mongodb.client.MongoClient
  import com.mongodb.client.MongoClients

  val mongoClient: MongoClient = MongoClients.create(connectUri)

  val database  = mongoClient.getDatabase(dbName).withWriteConcern(WriteConcern.MAJORITY)
  private val environmentCollection = database.getCollection("environment")
  private val levelStateCollection= database.getCollection("levelState")
  private val fillCollection= database.getCollection("fill")
  private val newState= database.getCollection("pin")

  def persistEnvironemnt(json: String): Unit = {
    val document: Document = Document.parse(json)
    val insertOneResult = environmentCollection.insertOne(document)
  }
  def persistFill(json: String): Unit = {
    val document: Document = Document.parse(json)
    val insertOneResult = fillCollection.insertOne(document)
  }
  def persistNewState(json: String): Unit = {
    val document: Document = Document.parse(json)
    val insertOneResult = newState.insertOne(document)
  }

  def persistLevel(levelState: String): Unit = {
    val document1 = new Document()
    document1.append("levelState", levelState)
    val insertOneResult = levelStateCollection.insertOne(document1)
  }
}
