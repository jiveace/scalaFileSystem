package org.iam.fp.filesystem

import org.iam.fp.files.Directory
import org.scalatest.flatspec.AnyFlatSpec

class StateTest extends AnyFlatSpec {
  val root = Directory.empty("", "root")
  val pwd = Directory.empty("", "workingDirectory")
  val output = "Output"

  val initialState = new State(root, pwd, output)

  "Constructor" must "create a new State with root, working directory and output" in {
    assert(initialState.root === root)
    assert(initialState.wd === pwd)
    assert(initialState.output === output)
  }

  "Initial input prompt" must "be '$ '" in {
    assert(State.PROMPT === "$ ")
  }

  "apply" must "return State with updated output when output provided" in {
    val appliedState = State(root, pwd, output)
    assert(appliedState.root === root)
    assert(appliedState.wd === pwd)
    assert(appliedState.output === output)
  }

  "apply" must "return State with blank output when not provided" in {
    val appliedState = State(null, null)
    assert(appliedState.output === "")
  }

  "setMessage" must "return input state with updated output" in {
    val newMessage = "Don't do drugs!"
    val newMessageState = initialState.setMessage(newMessage)
    assert(newMessageState.root === initialState.root)
    assert(newMessageState.wd === initialState.wd)
    assert(newMessageState.output === newMessage)
  }

}
