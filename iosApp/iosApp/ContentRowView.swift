//
//  ContentRowView.swift
//  iosApp
//
//  Created by Bobby Prabowo on 21/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import URLImage

struct ContentRowView: View {
    
    let content: Content
    
    var thumbnailUrl: URL {
        URL(string: content.thumbnailUrl) ?? URL(string: "")!
    }
    var body: some View {
        HStack {
            URLImage(url: thumbnailUrl,
                     content: { image in
                         image
                             .resizable()
                             .aspectRatio(contentMode: .fit)
                     })
            VStack {
                Text(content.title)
                    .font(.headline)
                Text(content.contentDescription)
                    .font(.subheadline)
            }
        }
    }
}

struct ContentRowView_Previews: PreviewProvider {
    static var previews: some View {
        ContentRowView(content: Content(title: "Dummy Title", presenterName: "Dummy Name", contentDescription: "Dummy Description", thumbnailUrl: "https://dummyurl.com/dummyimage", videoUrl: "https://dummyurl.com/dummyVideo", videoDuration: 120))
    }
}
