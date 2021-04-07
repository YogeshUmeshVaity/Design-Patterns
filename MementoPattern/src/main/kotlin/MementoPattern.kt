// Example and comments taken from https://refactoring.guru/design-patterns/memento

/**
 * The Originator class can produce snapshots of its own state, as well as restore its state from
 * snapshots when needed.
 */
class TextEditor(
    var text: String,
    var selectionWidth: Long,
    private var cursorPositionX: Long,
    private var cursorPositionY: Long,
) {
    fun setCursor(x: Long, y: Long) {
        cursorPositionX = x
        cursorPositionY = y
    }

    fun createSnapShot(): Snapshot =
        Snapshot(text, selectionWidth, cursorPositionX, cursorPositionY, this)

    override fun toString() = "$text $selectionWidth $cursorPositionX $cursorPositionY"
}

/**
 * The Memento is a value object that acts as a snapshot of the originator’s state. It’s a common
 * practice to make the memento immutable and pass it the data only once, via the constructor.
 */
class Snapshot(
    private val text: String,
    private val selectionWidth: Long,
    private val cursorPositionX: Long,
    private val cursorPositionY: Long,
    private val editor: TextEditor,
) {
    fun restore() {
        editor.text = text
        editor.setCursor(cursorPositionX, cursorPositionY)
        editor.selectionWidth = selectionWidth
    }
}

/**
 * The Caretaker knows not only “when” and “why” to capture the originator’s state, but also when
 * the state should be restored.
 *
 * A caretaker can keep track of the originator’s history by storing a stack of mementos. When the
 * originator has to travel back in history, the caretaker fetches the topmost memento from the
 * stack and passes it to the originator’s restoration method.
 */
class Command {
    private var snapshot: Snapshot? = null

    fun createSnapshot(textEditor: TextEditor) {
        snapshot = textEditor.createSnapShot()
    }

    fun undo() = snapshot?.restore()
}

fun main() {
    // Set initial state
    val textEditor = TextEditor("Welcome", 3, 0, 5)

    val command = Command()

    // Create snapshot of initial state
    val snapshot = command.createSnapshot(textEditor)

    // Print initial state
    println("Initial state: $textEditor")

    // Alter state
    textEditor.text = "Good night"
    textEditor.setCursor(1, 6)
    textEditor.selectionWidth = 4

    // Print altered state
    println("Altered state: $textEditor")

    // Restore state
    command.undo()

    // Print restored state
    println("Restored state: $textEditor")
}