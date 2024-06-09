package models
import scala.io.StdIn
import scala.collection.mutable.ListBuffer

object Main extends App {
  def main(args: Array[String]): Unit = {
 //val mystate = new MyState(continue = true, name = "")
  val userController = new UserController
  def initialState: MyState = MyState(continue = true)
  var sheets: ListBuffer[Sheet] = ListBuffer.empty[Sheet]
  var users: ListBuffer[User] = ListBuffer.empty[User]
  def menu: String =
    """|-----------Menu------------
       |1. Create a user
       |2. Create a sheet
       |3. Check a sheet
       |4. Change a value in a sheet
       |5. Change a sheet's access right
       |6. Collaborate with another user
       |----------------------------""".stripMargin
  def inputCommand: String = StdIn.readLine()
  
  def processInput(input: String, state: MyState): Unit = input match {
    
    case "1" => createNewUser()
    // case "2" => createSheet()
    // case "3" => checkSheet()
    case "exit" => exitSheet(state)
    case _ =>
      println("Invalid option. Please try again.")
      
  }
  def unCreateUser(Sheet: Sheet): Unit = {       
    println("Please create a new user")
            
  }
  def unCreateSheet(state: MyState): Unit = {
    println("Please create a new sheet")
    
  }

  def createNewUser(): Unit = {
    println("Please input your name: ")
    val newName = StdIn.readLine().trim    
    if(newName == ""){
      println("Invalid name, Please try again.")        
    }
    else{
      println(s"Create a user named \"$newName\"")
      userController.createUser(newName)     
    }
    
  }
  // def createSheet(sheets: MySheet): Unit = {
  //   println("Please input your name.")
  //   val Name = StdIn.readLine().trim
  //   sheets.foreach(userName => sheet.checkName(userName))
  //   if (sheets.userName == "") {
  //     unCreateUser(state)
      
  //   }else {
  //     println("Please name sheet name.")
  //     val sheetName = StdIn.readLine().trim
  //     println(s"Create a sheet named \"${sheetName}\" for ${state.name}")
  //     val matrix: Option[Array[Array[Int]]] = Some(Array.ofDim[Int](3, 3))
      
  //     matrix.getOrElse(Array.ofDim[Int](0, 0)).foreach(row => println(row.mkString(", ")))
  //     state.copy(sheet = (matrix))  
  //   }        
  // }


  // def checkSheet(state: MyState):MyState = {
  //   if(state.name == ""){
  //     unCreateUser(state)
  //   }else if(state.sheet.isEmpty){
  //     unCreateSheet(state)
  //   }else{

  //     matrix.getOrElse(Array.ofDim[Int](0, 0)).foreach(row => println(row.mkString(", ")))
  //   }
  //   state
  // }

  // def changeSheetValue(state: MyState):MyState ={
  //   checkSheet(state)

  // }
  
  def exitSheet(state: MyState) = {
    println("Game is over")
    state.copy(continue = false)
  }
  def gameLoop(state: MyState): Unit = {
    if (state.continue) {
      println(menu)
      val input = inputCommand.toLowerCase
      val nextState = processInput(input, state)
      gameLoop(state)
    }
  }
  gameLoop(initialState)

  
}
}