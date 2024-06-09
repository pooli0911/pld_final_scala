package models

case class Sheet(id: String, name: String, owner: User, matrix:Array[Array[Double]] , access: Map[User, AccessRights], globalaccess: AccessRights){

    def setGlobalAccess(newAccess: AccessRights): Sheet = {
        this.copy(globalaccess = newAccess)
    }
}
sealed trait AccessRights
case object ReadOnly extends AccessRights
case object Editable extends AccessRights