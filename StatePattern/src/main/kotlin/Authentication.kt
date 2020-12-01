sealed class AuthenticationState {
    abstract val isAuthenticated: Boolean
    abstract val userName: String
    abstract fun logIn(userName: String)
    abstract fun logOut()
}

/**
 * Changing the state is the responsibility of the state objects.
 */
class Authenticated(
    override val userName: String,
    private val context: AuthenticationPresenter
) : AuthenticationState() {

    override val isAuthenticated = true

    override fun logIn(userName: String) {
        println("${this.userName} already logged in")
    }

    override fun logOut() {
        context.state = UnAuthenticated("Unknown", context)
        println("$userName successfully logged out")
    }
}

class UnAuthenticated(
    override val userName: String,
    private val context: AuthenticationPresenter
) : AuthenticationState() {

    override val isAuthenticated = false

    override fun logIn(userName: String) {
        context.state = Authenticated(userName, context)
        println("$userName successfully logged in")
    }

    override fun logOut() {
        println("Not logged in yet")
    }
}