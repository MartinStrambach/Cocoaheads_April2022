import SwiftUI
import Combine
import shared

func test() -> AnyPublisher<Int, Never> {
	TimeRepository()
		.seconds()
		.publisher(with: ScopeFactory().createMainScope())
		.eraseToAnyPublisher()
}

struct ContentView: View {
	let greet = Greeting().greeting()
	@State private var timeStamp: Int = 0
	let time = test()

	var body: some View {
		VStack {
			Text(greet)
			Text("timestamp: \(timeStamp)")
				.onReceive(time) {
					timeStamp = $0
				}
		}
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
