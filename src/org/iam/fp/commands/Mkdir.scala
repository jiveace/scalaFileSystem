package org.iam.fp.commands

import org.iam.fp.files.{DirEntry, Directory}
import org.iam.fp.filesystem.State

class Mkdir(name: String) extends Command {

  def checkIllegal(name: String): Boolean =
    name.contains('.')

  def doMkdir(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if(path.isEmpty) {
        currentDirectory.addEntry(newEntry)
      } else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val wd = state.wd

    val allDirsInPath = wd.getAllFoldersInPath
    val newDirectory = Directory.empty(wd.path, name)
    val newRoot = updateStructure(state.root, allDirsInPath, newDirectory)
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

  override def apply(state: State): State = {
    val wd = state.wd
    if(wd.hesEntry(name)) {
      state.setMessage(s"Entry $name already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(s"$name must not contain separators")

    } else if(checkIllegal(name)) {
      state.setMessage(name + ": illegal entry name!")
    }else {
      doMkdir(state, name)
    }
  }
}