package simplefactory

class Database {
    val users = listOf("Sandy", "Vighu", "Dushy")
}

class UserRepository(database: Database) {
    val users = database.users
}

class UserViewModel(repository: UserRepository) {
    val users = repository.users
}

/**
 * The class UserView doesn't have to know about the Database and UserRepository.
 * This makes the class easy to test.
 * No need for a complex constructor.
 */
class UserView {
    // Responsibility of object creation given to another class
    private val viewModel = InjectorUtils.provideUserViewModel()

    fun displayUsers() {
        println(viewModel.users)
    }
}

/**
 * Singleton
 */
object InjectorUtils {
    fun provideUserViewModel(): UserViewModel {
        // Constructs an object that has many dependencies.
        val database = Database()
        val userRepository = UserRepository(database)
        return UserViewModel(userRepository)
    }
}

fun main() = UserView().displayUsers()