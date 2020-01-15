package org.iam.fp.commands

import org.iam.fp.filesystem.State

class UnknownCommand extends Command{
  override def apply(state: State): State = state.setMessage(UnknownCommand.FAIL_MESSAGE)
}

object UnknownCommand {
  val FAIL_MESSAGE = "Command not found!"
}
