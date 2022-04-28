import shared
import UIKit

class GenericsIOS {
	func sentenceComposer1() -> String {
		let value = GenericsKt.functionWithGenericReturnValue() // Types inside dictionary are gone
		return "missing data types"
	}

	func sentenceComposer2() -> String {
		let value = GenericsKt.functionWithDataClassReturnValue() // Types are preserved
		return value.value1 + value.value2.first!.key.stringValue
	}
}
