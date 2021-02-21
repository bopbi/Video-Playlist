//
//  PlaylistViewModel.swift
//  iosApp
//
//  Created by Bobby Prabowo on 21/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

final class PlaylistViewModel: ObservableObject, FetchPlaylistResultListener {
    
    @Published var contents: [Content] = Array()
    
    func onFailed(throwable: KotlinThrowable) {
        
    }
    
    func onSuccess(playlist: [Content]) {
        contents = playlist
    }
    
    init() {
        let videoAPi = VideoApi()
        let database = Platform.init().database
        
        let repository = ContentRepositoryImpl(videoApi: videoAPi, database: database)
        let fetchPlaylist = InitialFetchPlaylistImpl(contentRepository: repository)
        fetchPlaylist.execute(resultListener:self)
    }
}

extension Content : Identifiable { }
