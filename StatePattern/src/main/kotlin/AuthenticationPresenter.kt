/**
 * What the logIn() and logOut() functions will do depends on the current state,
 * we don't have to check using the conditionals like if..else or when. This is how the class
 * changes its behaviour depending on the state they are currently in.
 */
class AuthenticationPresenter {
    var state: AuthenticationState = UnAuthenticated("Unknown", this)

    val isAuthenticated = state.isAuthenticated

    val userName = state.userName

    fun logIn(userName: String) { state.logIn(userName) }

    fun logOut() { state.logOut() }
}

fun main() {
    val authenticationPresenter = AuthenticationPresenter()
    authenticationPresenter.logIn("John")
    assert(authenticationPresenter.isAuthenticated)
    assert(authenticationPresenter.userName == "John")
    authenticationPresenter.logIn("Jane")

    authenticationPresenter.logOut()
    assert(!authenticationPresenter.isAuthenticated)
    assert(authenticationPresenter.userName == "Unknown")

    authenticationPresenter.logOut()
}