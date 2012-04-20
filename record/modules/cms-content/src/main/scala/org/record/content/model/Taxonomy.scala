package org.record.content.model

import scala.collection.mutable.ListBuffer

class Taxonomy {

	// TODO: Divide into value, value sets and compound taxonomies
	var key : String = _;
	var value : String = _;
	var children : ListBuffer[Taxonomy] = _;
}