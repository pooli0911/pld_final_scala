
// package scala
// import javax.inject._
// import play.api.{Configuration, Environment}
// import play.api.mvc.ControllerComponents

// @Singleton
// class UserControllerProvider @Inject()(cc: ControllerComponents, configuration: Configuration) extends Provider[UserController] {
//   override def get(): UserController = {
//     val controller = new UserController(cc, configuration)
//     UserController.initialize(controller)
//     controller
//   }
// }