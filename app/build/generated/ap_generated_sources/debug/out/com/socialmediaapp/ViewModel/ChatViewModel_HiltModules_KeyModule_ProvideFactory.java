// Generated by Dagger (https://dagger.dev).
package com.socialmediaapp.ViewModel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.internal.lifecycle.HiltViewModelMap.KeySet")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ChatViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static ChatViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(ChatViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final ChatViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ChatViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
