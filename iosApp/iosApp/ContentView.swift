import SwiftUI
import shared

struct ContentView: View {
    
    @EnvironmentObject var viewmodel: PlaylistViewModel
    
    var body: some View {
        NavigationView {
            List {
                ForEach(viewmodel.contents) { content in
                    NavigationLink(destination: ContentDetailView(content: content)) {
                            ContentRowView(content: content)
                        }
                }
            }
            .navigationTitle("Video Playlist")
            .onAppear {
                viewmodel.performFetch()
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
