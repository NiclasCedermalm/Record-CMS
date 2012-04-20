package org.record.content.behaviour

trait VersionedItem {

  def getVersion() : Int = {
    return 0;
  }
  
  def getPreviousVersion() : VersionedItem = {
    return null;
  }
}