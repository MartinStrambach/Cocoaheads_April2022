import shared

class SuspendFunction {

	func suspendKotlin() {
		let manager = SuspendFunctionManager()
		manager.insert(value: 1) { par1, par2 in }
	}

	func suspendWrapped() {
		let wrapper = SuspendFunctionWrapper(scope: ScopeFactory().createDefaultScope())
		wrapper.insert(value: 1)
	}
}
