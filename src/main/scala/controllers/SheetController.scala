package controllers
import models.User
import scala.collection.mutable
import controllers.UserController
import models.{Sheet, User, Cell, AccessRights, ReadOnly, Editable}
import models.Cell
import scala.util.Try

class SheetController(){
    val userController = new UserController()
    private val accessRightsEnabled = false
    private val sheets = mutable.Map[String, Sheet]()
    private var sheetCounter = 0
    
    def createSheet(name:String, ownerName:User): Unit = {
        val id = s"${sheetCounter + 1}"  
        val matrix: Array[Array[Double]] = Array.ofDim[Double](3, 3)        
        val sheet = Sheet(id, name, ownerName, matrix, Map(ownerName -> Editable), ReadOnly)
        sheets += (id -> sheet)        
        println(s"Sheet $name created with ID: $id")
        sheetCounter += 1
    
       
    }
   def printSheet(sheetId: String): Unit = {
        sheets.get(sheetId) match{
            case Some(sheet) => sheet.matrix.foreach(row => println(row.mkString(", ")))
            case None => println("Sheet not found")
        }
   }

   def updateCell(sheetId: String, row: String, col: String, content: String, user: User): Unit = {
        sheets.get(sheetId) match{
        case Some(sheet) =>            
        val memberAccess = sheet.access.getOrElse(user, ReadOnly)
        if (sheet.globalaccess ==Editable || sheet.owner == user || memberAccess == Editable) {            
            val changecontent = compute(content)
            if (!List("1", "2", "3").contains(row)) {
                println("out of bounds")
            }else if(!List("1", "2", "3").contains(col)){
                println("out of bounds")
            }else{
            sheet.matrix(row.toInt - 1 )(col.toInt - 1) = changecontent.toDouble
            sheet.matrix.foreach(row => println(row.mkString(", ")))
            }
        }else{
            println("Access denied")
        }
        case None => println("Sheet not found")
    }
   }
 def changeAccess(sheetId: String, user: User, access: String): Unit = {
    
        sheets.get(sheetId) match {
          case Some(sheet) =>
            val rights = access match {
              case "read" => ReadOnly
              case "edit" => Editable
              case _ => ReadOnly // Default case
            }
            val updatedSheet = sheet.setGlobalAccess(rights)
            sheets.update(sheetId, updatedSheet)
            println(s"Access rights updated for user: '${user.name}'")
          case None => println("Sheet not found")
        }
     
  }
  def shareSheets(sheetId: String, user: User, access: String, member:User): Unit = {
    sheets.get(sheetId) match {
        case Some(sheet) =>          
            val rights = access match {
                case "read" => ReadOnly
                case "edit" => Editable
                case _ => ReadOnly // Default case
              }
              val updatedAccess = sheet.access.updated(member, rights)
              sheets.update(sheetId, sheet.copy(access = updatedAccess))
              println(s"Sheet shared with user $member with $access access")
        case None => println("Sheet not found")
      }
    }
    
  

   

   def compute(expression: String): String = {
    val tokens = expression.split("\\s+").toList
    
    val result = tokens match {
        case a :: op :: b :: Nil =>        
        val aValueOpt = Try(a.toDouble).toOption
        val bValueOpt = Try(b.toDouble).toOption        
        
        aValueOpt.getOrElse {
            println(s"Invalid number format for: $a")
            0.0
        } + bValueOpt.getOrElse {
            println(s"Invalid number format for: $b")
            0.0
        }
        
        case _ =>
        println("Invalid expression format")
        0.0
    }
    
    result.toString
    }       
}