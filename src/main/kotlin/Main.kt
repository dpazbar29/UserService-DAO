import dao.UserDAOH2
import dao.entity.UserEntity
import services.UserServiceImpl
import sql.utils.DataSourceFactory

fun main() {
    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val connection = dataSource.connection

    val query = connection.prepareStatement("CREATE TABLE tuser(\n" +
            "                      id VARCHAR(36) PRIMARY KEY,\n" +
            "                      name VARCHAR(255),\n" +
            "                      email VARCHAR(255)\n" +
            ");")

    // the query is executed and results are fetched
    query.executeUpdate()

    // Creamos la instancia de UserDAO
    val userDao = UserDAOH2(dataSource)

    // Creamos la instancia de UserService
    val userService = UserServiceImpl(userDao)

    // Creamos un nuevo usuario
    val newUser = UserEntity(name = "John Doe", email = "johndoe@example.com")
    var createdUser = userService.create(newUser)
    println("Created user: $createdUser")

    // Obtenemos un usuario por su ID
    val foundUser = userService.getById(createdUser.id)
    println("Found user: $foundUser")

    // Actualizamos el usuario
    val updatedUser = foundUser!!.copy(name = "Jane Doe")
    val savedUser = userService.update(updatedUser)
    println("Updated user: $savedUser")

    val otherUser = UserEntity(name = "Eduardo Fernandez", email = "eferoli@gmail.com")
    createdUser = userService.create( otherUser)
    println("Created user: $createdUser")


    // Obtenemos todos los usuarios
    var allUsers = userService.getAll()
    println("All users: $allUsers")

    // Eliminamos el usuario
    userService.delete(savedUser.id)
    println("User deleted")

    // Obtenemos todos los usuarios
    allUsers = userService.getAll()
    println("All users: $allUsers")

    // Eliminamos el usuario
    userService.delete(otherUser.id)
    println("User deleted")
}


