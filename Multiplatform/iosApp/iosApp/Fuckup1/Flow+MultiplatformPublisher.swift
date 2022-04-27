extension Flow {
	func publisher<Output, Failure>(
		with scope: CoroutineScope
	) -> MultiplatformPublisher<Output, Failure> {
		MultiplatformPublisher<Output, Failure>(flow: self, scope: scope)
	}
}
