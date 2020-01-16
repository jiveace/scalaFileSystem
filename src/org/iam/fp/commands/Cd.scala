package org.iam.fp.commands

import org.iam.fp.files.Directory
import org.iam.fp.filesystem.State

import scala.annotation.tailrec

class Cd(dir: String) extends Command {

  override def apply(state: State): State = {

    val root = state.root
    val wd = state.wd

    val absolutePath =
      if (dir.startsWith(Directory.SEPARATOR)) dir
      else if (wd.isRoot) wd.path + dir
      else wd.path + Directory.SEPARATOR + dir

    val destinationDirectory = doFindEntry(root, absolutePath)

    if (destinationDirectory == null || !destinationDirectory.isDirectory) {
      state.setMessage(dir + ": no such directory")
    } else
      State(root, destinationDirectory.asDirectory)
  }


  def doFindEntry(root: Directory, path: String): Directory = {
    @tailrec
    def findEntryHelper(currentDirectory: Directory, path: List[String]): Directory =
      if (path.isEmpty || path.head.isEmpty) currentDirectory
      else if (path.tail.isEmpty) currentDirectory.findEntry(path.head).asDirectory
      else {
        val nextDir = currentDirectory.findEntry(path.head)
        if (nextDir == null || !nextDir.isDirectory) null
        else findEntryHelper(nextDir.asDirectory, path.tail)
      }


    // 1. tokens
    val tokens = path.substring(1).split(Directory.SEPARATOR).toList

    // 2. Navigate to the correct entry
    findEntryHelper(root, tokens)
  }

}
