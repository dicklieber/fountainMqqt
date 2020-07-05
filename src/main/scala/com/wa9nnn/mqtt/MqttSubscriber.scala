
package com.wa9nnn.mqtt

import com.typesafe.scalalogging.LazyLogging
import org.eclipse.paho.client.mqttv3.{IMqttDeliveryToken, MqttCallback, MqttClient, MqttMessage}
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence

class MqttSubscriber  extends MqttCallback with LazyLogging{
  private val mongo = new Mongo

  private val brokerUrl = "tcp://192.168.0.5:1883"
//  private val topicBase = "fountain"

  val persistence = new MqttDefaultFilePersistence("/tmp")
  var client = new MqttClient(brokerUrl, MqttClient.generateClientId, persistence)
  client.connect()
  client.setCallback(this)
  client.subscribe("fountain/#")

  override def connectionLost(cause: Throwable): Unit = {
    logger.error(s"Connection Lost", cause)

  }

  override def messageArrived(topic: String, message: MqttMessage): Unit = {
    val topicLast = topic.split("/").last
    val string = message.toString
    topicLast match {
      case "levelState" =>
        mongo.persistLevel(string)
      case "environment" =>
        mongo.persistEnvironemnt(string)
      case "newState" =>
        mongo.persistNewState(string)
      case "fill" =>
        mongo.persistFill(string)
case x =>
        logger.error(x)

    }

  }

  override def deliveryComplete(token: IMqttDeliveryToken): Unit = {
    logger.debug(s"DeliveryComplete: $token")

  }
}
