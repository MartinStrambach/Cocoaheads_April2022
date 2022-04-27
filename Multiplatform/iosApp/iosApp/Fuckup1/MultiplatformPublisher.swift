import Foundation
import Combine
import shared

// Based on https://johnoreilly.dev/posts/kotlinmultiplatform-swift-combine_publisher-flow/
public struct MultiplatformPublisher<Output, Failure: Error>: Publisher {

	private let flow: Flow
	private let scope: CoroutineScope

	public init(flow: Flow, scope: CoroutineScope) {
		self.flow = flow
		self.scope = scope
	}

	public func receive<S: Subscriber>(subscriber: S) where S.Input == Output, S.Failure == Failure {
		let subscription = CustomSubscription(flow: flow, scope: scope, subscriber: subscriber)
		subscriber.receive(subscription: subscription)
	}

	final class CustomSubscription<S: Subscriber>: Subscription where S.Input == Output, S.Failure == Failure {

		private var subscriber: S?
		private let job: Job?
		private let flow: Flow

		init(
			flow: Flow,
			scope: CoroutineScope,
			subscriber: S
		) {
			self.flow = flow
			self.subscriber = subscriber

			job = FlowUtilsKt.wrapFlow(flow: flow).subscribe(
				scope: scope,
				onEach: { result in
					_ = subscriber.receive(result as! Output) // swiftlint:disable:this force_cast
				},
				onComplete: { subscriber.receive(completion: .finished) },
				onThrow: { error in
					subscriber.receive(completion: .failure(error.asError() as! Failure)) // swiftlint:disable:this force_cast
				}
			)
		}

		func cancel() {
			subscriber = nil
			job?.cancel(cause: nil)
		}

		func request(_ demand: Subscribers.Demand) {}

	}

}
