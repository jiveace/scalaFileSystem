package org.iam.fp.commands

import org.iam.fp.filesystem.State

trait Command {
  def apply(state: State): State
}

object Command {
  def from(input: String): Command =
    new UnknownCommand
}
