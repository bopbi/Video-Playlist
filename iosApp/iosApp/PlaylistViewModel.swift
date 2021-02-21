//
//  PlaylistViewModel.swift
//  iosApp
//
//  Created by Bobby Prabowo on 21/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

final class PlaylistViewModel: ObservableObject, UseCaseSuccessListener, UseCaseFailureListener {
    
    @Published var contents: [Content] = Array()
    var fetchPlaylist: InitialFetchPlaylist?
    var loadPlaylist: InitialLoadPlaylist?
    
    func onSuccess(tag: String, result: Any?) {
        if (result is [Content]) {
            self.contents = result as! [Content]
        }
    }
    
    func onError(tag: String, throwable: KotlinThrowable) {
        
    }
    
    init() {
        let videoAPi = VideoApi()
        let database = Platform.init().database
        let repository = ContentRepositoryImpl(videoApi: videoAPi, database: database)
        self.fetchPlaylist = InitialFetchPlaylistImpl(contentRepository: repository, successListener: self, failureListener: self)
        self.loadPlaylist = InitialLoadPlaylistImpl(repository: repository, successListener: self, failureListener: self)
    }
    
    func performLoadAndFetch() {
        self.fetchPlaylist?.executeCallback(processTag: "PlayListFetch")
        self.loadPlaylist?.executeCallback(requestTag: "PlaylistLoad")
    }
    
}

extension Content : Identifiable { }
