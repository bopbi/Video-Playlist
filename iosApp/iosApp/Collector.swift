//
//  FlowCollector.swift
//  iosApp
//
//  Created by Bobby Prabowo on 26/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class Collector<T>: Kotlinx_coroutines_coreFlowCollector {
    
    let callback:(T) -> Void
    
    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }
    
    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        callback(value as! T)
        
        completionHandler(KotlinUnit(), nil)
    }
    
    
}
