import SwiftUI
import shared

struct ContentView: View {
    
    @EnvironmentObject var viewmodel: PlaylistViewModel
    
    var body: some View {
        List {
            ForEach(viewmodel.contents) { content in
                ContentRowView(content: content)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(PlaylistViewModel())
    }
}
