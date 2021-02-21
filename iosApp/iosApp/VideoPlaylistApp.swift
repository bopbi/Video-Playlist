//
//  VideoPlaylistApp.swift
//  iosApp
//
//  Created by Bobby Prabowo on 21/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

@main
struct VideoPlaylistApp: App {
    
    @StateObject private var playlistViewModel = PlaylistViewModel()

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(playlistViewModel)
        }
    }
}
