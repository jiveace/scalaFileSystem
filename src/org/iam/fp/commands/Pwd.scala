package org.iam.fp.commands

import org.iam.fp.filesystem.State

class Pwd extends Command{
  override def apply(state: State): State =
    state.setMessage(state.wd.path)
}
