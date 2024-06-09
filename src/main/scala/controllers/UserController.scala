package controllers
import models.User
import scala.collection.mutable

class UserController(){
  private val users = mutable.Map[String, User]()
  private val accessRightsEnabled: Boolean = false
  private val sharingEnabled: Boolean = false
  def createUser(name:String): Unit = {
    val user = User(name)
    users += (user.name -> user)
  }
  def getUser(name: String): Option[User] = {
     
    users.get(name)
  }
}
