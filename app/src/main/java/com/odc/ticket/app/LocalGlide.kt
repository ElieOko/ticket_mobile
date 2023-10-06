package com.odc.ticket.app

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class LocalGlide : AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}