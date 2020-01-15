package org.iam.fp.commands

import org.iam.fp.files.Directory
import org.iam.fp.filesystem.State
import org.scalatest.flatspec.AnyFlatSpec

class UnknownCommandTest extends AnyFlatSpec {

  val root = Directory.ROOT;
  val mockOutput = "mockStateOutput"
  val mockState = new State(root, root, mockOutput)
  val inputState = new State(root, root, "inputStateOutput"){
    override def setMessage(m: String): State = mockState
  }

  "apply" must "return output from its inputs setMessage function" in {
    val appliedState = new UnknownCommand()(inputState)
    assert(appliedState.root === mockState.root)
    assert(appliedState.wd === mockState.wd)
    assert(appliedState.output === mockOutput)
  }
}
