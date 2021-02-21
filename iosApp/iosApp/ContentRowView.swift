//
//  ContentRowView.swift
//  iosApp
//
//  Created by Bobby Prabowo on 21/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ContentRowView: View {
    
    let content: Content
    var body: some View {
        Text(content.title)
    }
}

struct ContentRowView_Previews: PreviewProvider {
    static var previews: some View {
        ContentRowView(content: Content(title: "Dummy Title", presenterName: "Dummy Name", description: "Dummy Description", thumbnailUrl: "https://dummyurl.com/dummyimage", videoUrl: "https://dummyurl.com/dummyVideo", videoDuration: 120))
    }
}
