package org.cibseven.community.bpm.data.writer

/**
 * Inverting calls to [org.cibseven.community.bpm.data.adapter.WriteAdapter].
 *
 * @param <S> type of concrete Writer for fluent usage.
</S> */
interface VariableWriter<S : VariableWriter<S>> : LocalVariableWriter<S>, GlobalVariableWriter<S>
