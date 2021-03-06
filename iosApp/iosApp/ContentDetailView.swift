//
//  ContentDetailView.swift
//  iosApp
//
//  Created by Bobby Prabowo on 21/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import AVKit

struct ContentDetailView: View {
    
    let content: Content
    
    var body: some View {
        NavigationView {
            VStack {
                VideoPlayer(player: AVPlayer(url:  URL(string: content.videoUrl)!))
            }
                .navigationTitle(content.title)
        }
    }
}

struct ContentDetailView_Previews: PreviewProvider {
    static var previews: some View {
        ContentDetailView(content: Content(title: "Dummy Title", presenterName: "Dummy Name", contentDescription: "Dummy Description", thumbnailUrl: "https://dummyurl.com/dummyimage", videoUrl: "https://dummyurl.com/dummyVideo", videoDuration: 120))
    }
}
