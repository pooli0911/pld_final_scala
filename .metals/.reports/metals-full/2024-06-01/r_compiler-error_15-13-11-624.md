file:///D:/pldfinal/src/main/scala/Main.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 3018
uri: file:///D:/pldfinal/src/main/scala/Main.scala
text:
```scala
package models
import scala.io.StdIn
import scala.collection.mutable.ListBuffer
import controllers.UserController
import controllers.SheetController
import models.Sheet
import models.User
import models.Cell
object Main extends App {
  class MyState(var continue: Boolean)
  var state: MyState = new MyState(continue = true)
  val userController = new UserController()
  val sheetController = new SheetController()
  
  def menu: String =
    """|-----------Menu------------
1. Create a user
2. Create a sheet
3. Check a sheet
4. Change a value in a sheet
5. Change a sheet's access right
6. Collaborate with another user
----------------------------""".stripMargin
  def inputCommand: String = StdIn.readLine()
  
  def processInput(input: String, state: MyState): Unit = input match {
    
    case "1" => createNewUser()
    case "2" => createSheet()
    case "3" => checkSheet()
    case "4" => changeSheetValue()
    case "5" => changeSheetRight()
    //case "6" => collaborateWithUser()
    case "exit" => exitSheet(state)
    case _ =>
      println("Invalid option. Please try again.")
      
  }
  

  def createNewUser(): Unit = {
    println("Please input your name: ")
    val newName = StdIn.readLine().trim       
    if(newName == ""){
      println("Invalid name, Please try again.")        
    }else if(!userController.getUser(newName).isEmpty){
      println("User already exists!")
    }else{
      println(s"Create a user named \"$newName\"")
      userController.createUser(newName)     
    }
    
  }
  def createSheet(): Unit = {
    println("Please input your name: ")
    val Name = StdIn.readLine().trim
    println("Please create a Sheet Name: ")
    val SheetName = StdIn.readLine().trim
    val userOption: Option[User] = userController.getUser(Name)
    userOption match {
    case Some(user) =>
      sheetController.createSheet(SheetName, user)
      println(s"Sheet $SheetName created for user $Name.") 
       
    case None =>
      println(s"User $Name does not exist, cannot create sheet.")
    }               
  }


  def checkSheet(): Unit = {
    println("Please enter your Sheet Id: ")
    val sheetId = StdIn.readLine().trim
    sheetController.printSheet(sheetId)
  }

  def changeSheetValue():Unit ={
    println("Please enter your Name: ")
    val Name = StdIn.readLine().trim
    println("Please enter your Sheet Id: ")
    val sheetId = StdIn.readLine().trim
    println("Please enter the row : ")
    val row = StdIn.readLine().trim
    println("Please enter the column: ")
    val column = StdIn.readLine().trim
    println("Please enter the value: number <blank> operator <blank> number ")
    val value = StdIn.readLine().trim
    val userOption: Option[User] = userController.getUser(Name)
    userOption match {
      case Some(user) => 
      sheetController.updateCell(sheetId, row, column, value)
      case None(@@)
  }
  def changeSheetRight():Unit = {
    println("Please input your name: ")
    val Name = StdIn.readLine().trim
    println("Please input your sheet Id: ")
    val sheetId = StdIn.readLine().trim
    println("Please enter the access: read or edit ")
    val access = StdIn.readLine().trim.toLowerCase()
    println("Change the access to who? ")
    val otherUserName = StdIn.readLine().trim
    val userOption: Option[User] = userController.getUser(Name)
    userOption match {
    case Some(user) =>
      val OtherUserName: Option[User] = userController.getUser(Name)
      OtherUserName match {
      case Some(otherUser) =>
      sheetController.changeAccess(sheetId, user, access, otherUser)       
      case None =>
        println(s"User $Name does not exist, cannot change sheet.")
      }
    case None =>
      println(s"User $Name does not exist, cannot change sheet.")
    }  
  }
  
  def exitSheet(state: MyState) = {
    println("Game is over")
    state.continue = false
  }
  def gameLoop(state: MyState): Unit = {
    if (state.continue) {
      println(menu)
      val input = inputCommand.toLowerCase
      val nextState = processInput(input, state)
      gameLoop(state)
    }
  }
  gameLoop(state)

  
}

```



#### Error stacktrace:

```
dotty.tools.dotc.core.SymDenotations$NoDenotation$.owner(SymDenotations.scala:2607)
	scala.meta.internal.pc.SignatureHelpProvider$.isValid(SignatureHelpProvider.scala:83)
	scala.meta.internal.pc.SignatureHelpProvider$.notCurrentApply(SignatureHelpProvider.scala:92)
	scala.meta.internal.pc.SignatureHelpProvider$.$anonfun$1(SignatureHelpProvider.scala:48)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile(LinearSeq.scala:280)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile$(LinearSeq.scala:278)
	scala.collection.immutable.List.dropWhile(List.scala:79)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:48)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:412)
```
#### Short summary: 

java.lang.AssertionError: NoDenotation.owner